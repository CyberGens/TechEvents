<?php

namespace ClientBundle\Controller;



use ClientBundle\Entity\Reclamation;
use ClientBundle\Form\ReclamationType;
use ClientBundle\Entity\RepRec;

use ClientBundle\Entity\Produit;
use ClientBundle\Entity\Commentaires;
use ClientBundle\Form\CommentairesType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;




class ReclamationAdminController extends Controller
{
    public function reclamationsAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $reclamations = $em->getRepository("ClientBundle:Reclamation")->findAll();
        $paginator = $this->get('knp_paginator');

        $reclamations = $paginator->paginate(
            $reclamations,
            $request->query->getInt('page', 1)/*page number*/,
            $request->query->getInt('limit', 4)/*limit per page*/
        );
        $notif = $this->get('mgilet.notification');
        if ($request->isXmlHttpRequest()) {
            foreach ($notif as $n) {
                $nn = $this->get('mgilet.notification');
                $nn->setSeen(1);
                $this->getDoctrine()->getManager()->persist($nn);
                $this->getDoctrine()->getManager()->flush();
            }
            $notif = $this->get('mgilet.notification')->getAllUnseen();
            $count = count($notif);
            return new JsonResponse($count);
        }
        $count = count($notif);


        return $this->render("@Client/Reclamation/reclamations.html.twig", array('blog_posts' => $reclamations, 'count' => $count, 'notif' => $notif));
    }

    public function DeletereclamationAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $reclamations = $em->getRepository('ClientBundle:Reclamation')->find($id);
        $em->remove($reclamations);
        $em->flush();
        return $this->redirectToRoute("reclamations");
    }







}
