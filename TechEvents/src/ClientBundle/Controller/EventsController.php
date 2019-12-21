<?php

namespace ClientBundle\Controller;

use ClientBundle\Entity\Events;
use ClientBundle\Entity\Participation;
use ClientBundle\Form\EventsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class EventsController extends Controller
{
    public function addAction(Request $request)
    {
        $E= new Events();
        $Form=$this->createForm(EventsType::class,$E);
        $Form->handleRequest($request);
        if ($Form->isValid()){
            $dir="C:\\wamp64\\www\\madame\\web\\imagesEvents";
            $file=$Form['image']->getData();
            $E->setImage($E->getTitre().".png");
            $file->move($dir,$E->getImage());
            $E->setEtat(0);
            $E->setNbreparticipation(0);
            $E->setUser($this->getUser());
            $em=$this->getDoctrine()->getManager();
            $em->persist($E);
            $em->flush();
            $manager = $this->get('mgilet.notification');
            $notif = $manager->createNotification('l evenement '.''.$E->getTitre().''.'est en attente');
            $notif->setMessage('Ajout d un evenement ');
            $notif->setLink('http://symfony.com/');
            $manager->addNotification(array($this->getUser()), $notif, true);
            return $this->redirectToRoute('Events_homepage');
        }
        return $this->render('ClientBundle:Events:add.html.twig', array("Form"=>$Form->createView()

        ));
    }

    public function updateAction(Request $request, $id)
    {

        $em=$this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $Event= $em->getRepository('ClientBundle:Events')->find($id);
        $form=$this->createForm(EventsType::class,$Event);
        $form->handleRequest($request);
        if ($Event->getUser()->getId() == $idu) {
            if ($form->isValid()) {

                $dir = "C:\\wamp64\\www\\madame\\web\\imagesEvent";

                $file = $form['image']->getData();
                $Event->setImage($Event->getTitre() . ".png");

                $file->move($dir, $Event->getImage());
                $em->persist($Event);
                $em->flush();
                return $this->redirect($this->generateUrl("Events_homepage"));

                $em->persist($Event);
                $em->flush();
                //  return $this->redirectToRoute('Events_homepage');
            }
            return $this->render("ClientBundle:Events:update.html.twig", array("form" => $form->createView()));
        }
        $msg='Vous n avez pas le droit de modifier un evenement qui n est pas le tien ! ';
        $etat=1;
        $events=$em->getRepository(Events::class)->TrierEventsClient($etat);

        return $this->render('ClientBundle:Default:Evenement.html.twig',array('MessageEvent'=>$msg,'events'=>$events));


    }

    public function EventsAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $etat=1;
        $events=$em->getRepository(Events::class)->TrierEventsClient($etat);
        $paginator  = $this->get('knp_paginator');

        $events = $paginator->paginate(
            $events,
            $request->query->getInt('page', 1)/*page number*/,
            $request->query->getInt('limit', 3)/*limit per page*/
        );

        $msg='';
        return $this->render('ClientBundle:Default:Evenement.html.twig',array('MessageEvent'=>$msg,'blog_posts'=>$events));

    }


    public function EventsDoneAction()
    {
        $em=$this->getDoctrine()->getManager();
        $etat=1;
        $events=$em->getRepository(Events::class)->TrierEventsDoneClient($etat);
        $msg='';
        return $this->render('ClientBundle:Default:Evenement.html.twig',array('MessageEvent'=>$msg,'events'=>$events));

    }

    public function MyEventsAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $user=$this->getUser();
        $id=$user->getId();
        $events=$em->getRepository(Events::class)->findBy(array("user" => $id));
        $msg='';
        return $this->render('ClientBundle:Default:Evenement.html.twig',array('MessageEvent'=>$msg,'events'=>$events));


    }


    public function DeleteAction(Request $request)
    {
        $id=$request->get('id');
        $user=$this->getUser();
        $idu=$user->getId();
        $em=$this->getDoctrine()->getManager();
        $Event=$em->getRepository("ClientBundle:Events")->find($id);
        if ($Event->getUser()->getId() == $idu) {

            $em->remove($Event);
        $em->flush();

//        $em=$this->getDoctrine()->getManager();
//        $enseigne=$em->getRepository("ClientBundle:Enseignes")->find($id);
//        $em->remove($enseigne);
//        $em->flush();
        return ($this->redirectToRoute("Events_homepage"));
        }
        $msg='Vous n avez pas le droit de supprimer un evenement qui n est pas le tien ! ';
        $etat=1;
        $events=$em->getRepository(Events::class)->TrierEventsClient($etat);

        return $this->render('ClientBundle:Default:Evenement.html.twig',array('MessageEvent'=>$msg,'events'=>$events));
    }

    public function readAction(Request $request)
    {
        $id=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $Event=$em->getRepository("ClientBundle:Events")->find($id);
        $participation=$em->getRepository('ClientBundle:Participation')->findOneBy(array('iduser'=>$idu,'idevent'=>$id));
        $iduser=$Event->getUser()->getId();
        $titre=$Event->getTitre();
        $image=$Event->getImage();
        $description=$Event->getDescription();

        if(empty($participation) )
        {
            if ($idu == $iduser)
            {
                $part = 0;
                $x = 1;
            }
            else
            { $part = 0;
                $x = 0;
            }
        }
        else
        {
            if ($idu == $iduser)
            {
                $part = 1;
                $x = 1;
            }
            else
            { $part = 1;
                $x = 0;
            }
        }
        return $this->render('ClientBundle:Events:read.html.twig', array('x'=>$x,'part' => $part, 'Titre' => $titre, 'image' => $image, 'desc' => $description, 'Event' => $Event));

    }
    public function rechercheEventAjaxDQLAction(Request $request)
    {
        if ($request->isXmlHttpRequest()) {
            $titre = $request->get('titre');
            $em = $this->getDoctrine()->getManager();
            $E = $em->getRepository(Events::class)->findEventDQL($titre);
            $ser = new Serializer(array(new ObjectNormalizer()));
            $data = $ser->normalize($E);
            return new JsonResponse($data);
        }
        return $this->render('ClientBundle:Events:ajax.html.twig', array());
    }
    public function ParticipantsAction(Request $request)
    {
        $idevent=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository("ClientBundle:Events")->find($idevent);

//        $Event= $em->getRepository('ClientBundle:Events')->find($idevent);
//        $user=$this->getUser();
//        $idu=$user->getId();
        $participants=$em->getRepository(Participation::class)->findParticipationDQL($idevent);
        $msg='';
        return $this->render('ClientBundle:Events:Participants.html.twig',array('MessageEvent'=>$msg,'participants'=>$participants));

    }


}
