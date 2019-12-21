<?php

namespace ClientBundle\Controller;

use ClientBundle\Entity\Commentaires;
use ClientBundle\Entity\Jaim;
use ClientBundle\Entity\User;
use ClientBundle\Form\CommentairesType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CommentairesController extends Controller
{
    public function addAction(Request $request)
    {
        $E = new Commentaires();
        $Form = $this->createForm(CommentairesType::class, $E);
        $Form->handleRequest($request);
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $idu = $user;
        if ($Form->isValid()) {
            $E->setIduse($idu);
            $em = $this->getDoctrine()->getManager();
            $em->persist($E);
            $em->flush();
            return $this->redirectToRoute('read_commentaire');
        }
        return $this->render('@Client/Commentaires/add.html.twig', array("Form" => $Form->createView()

        ));
    }

    public function updateAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $Commentaire = $em->getRepository('ClientBundle:Commentaires')->find($id);
        $form = $this->createForm(CommentairesType::class, $Commentaire);
        $form->handleRequest($request);
        if ($form->isValid()) {

            $em->flush();
            return $this->redirect($this->generateUrl("read_commentaire"));

            $em->persist($Commentaire);
            $em->flush();
            //  return $this->redirectToRoute('Events_homepage');
        }
        return $this->render("@Client/Commentaires/update.html.twig", array("form" => $form->createView()));
    }

    public function deleteAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $Commentaire = $em->getRepository("ClientBundle:Commentaires")->find($id);
        $em->remove($Commentaire);
        $em->flush();

//        $em=$this->getDoctrine()->getManager();
//        $enseigne=$em->getRepository("ClientBundle:Enseignes")->find($id);
//        $em->remove($enseigne);
//        $em->flush();
        return ($this->redirectToRoute("read_commentaire"));
    }

    public function readAction()
    {

        $em = $this->getDoctrine()->getManager();
        $commentaires = $em->getRepository(Commentaires::class)->findAll();
        return $this->render('@Client/Commentaires/read.html.twig', array('commentaires' => $commentaires));


    }

    public function LikeCommentAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $week = date("Y-m-d", strtotime("-1 week"));

        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Commentaires p
    WHERE p.date >=:d1 '
        );
        $query->setParameter('d1', $week);
        $commentaires = $query->getResult();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $idu = $user;
        $id = $request->get('id');
        $Commentaire = $em->getRepository('ClientBundle:Commentaires')->find($id);
        $jaim = $em->getRepository('ClientBundle:Jaim')->findOneBy(array('idClient' => $idu->getId(), 'idroduit' => $id));
        //$user = $em->getRepository('ClientBundle:User')->find(4);


        if (empty($jaim)) {

            $jaim=new Jaim();
            $jaim->setEtat(1);
            $jaim->setId(4);
            $Commentaire->setNbjaimes($Commentaire->getNbjaimes() + 1);
            $jaim->setidClient($idu);
            $jaim->setIdroduit($Commentaire);
            $em->persist($jaim);
            $em->persist($Commentaire);
            $em->flush();
            ?>
            <script>alert('thanks for ur like <3 ');</script><?php

            return $this->render('@Client/Commentaires/read.html.twig', array('commentaires' => $commentaires));

        }


        ?>
        <script>alert('u already like it :p ');</script><?php

        return $this->render('@Client/Commentaires/read.html.twig', array('commentaires' => $commentaires));

}


}
