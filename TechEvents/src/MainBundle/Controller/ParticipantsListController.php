<?php

namespace MainBundle\Controller;

use MainBundle\Entity\ParticipantsList;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;


/**
 * Participantslist controller.
 *
 */
class ParticipantsListController extends Controller
{
    /**
     * Lists all participantsList entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $participantsLists = $em->getRepository('MainBundle:ParticipantsList')->findAll();

        return $this->render('participantslist/index.html.twig', array(
            'participantsLists' => $participantsLists,
        ));
    }

    /**
     * Finds and displays a participantsList entity.
     *
     */
    public function showAction(ParticipantsList $participantsList)
    {

        return $this->render('participantslist/show.html.twig', array(
            'participantsList' => $participantsList,
        ));
    }








}
