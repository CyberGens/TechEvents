<?php

namespace MainBundle\Controller;

use MainBundle\Entity\Events;
use MainBundle\Entity\Inscription;
use MainBundle\Entity\ParticipantsList;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Inscription controller.
 *
 */
class InscriptionController extends Controller
{
    /**
     * Lists all inscription entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $inscriptions = $em->getRepository('MainBundle:Inscription')->findAll();

        return $this->render('inscription/index.html.twig', array(
            'inscriptions' => $inscriptions,
        ));
    }

    /**
     * Creates a new inscription entity.
     *
     */
    public function newAction(Request $request)
    {
        $inscription = new Inscription();
        $form = $this->createForm('MainBundle\Form\InscriptionType', $inscription);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription);
            $em->flush();

            return $this->redirectToRoute('inscription_show', array('idInscription' => $inscription->getIdInscription()));
        }

        return $this->render('inscription/new.html.twig', array(
            'inscription' => $inscription,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a inscription entity.
     *
     */
    public function showAction(Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);

        return $this->render('inscription/show.html.twig', array(
            'inscription' => $inscription,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing inscription entity.
     *
     */
    public function editAction(Request $request, Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);
        $editForm = $this->createForm('MainBundle\Form\InscriptionType', $inscription);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('inscription_edit', array('idInscription' => $inscription->getIdInscription()));
        }

        return $this->render('inscription/edit.html.twig', array(
            'inscription' => $inscription,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a inscription entity.
     *
     */
    public function deleteAction(Request $request, Inscription $inscription)
    {
        $form = $this->createDeleteForm($inscription);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($inscription);
            $em->flush();
        }

        return $this->redirectToRoute('inscription_index');
    }

    /**
     * Creates a form to delete a inscription entity.
     *
     * @param Inscription $inscription The inscription entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Inscription $inscription)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('inscription_delete', array('idInscription' => $inscription->getIdInscription())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    public function affichelistAction()
    {
        $em = $this->getDoctrine()->getManager();

        $event = $em->getRepository('MainBundle:Events')->findAll();

        return $this->render('events/participation.html.twig', array(
            'events' => $event,
        ));
    }


    public function participerAction(Events $idEvent)
    {
        $inscriptionn = new Inscription();
        $em = $this->getDoctrine()->getManager();
$inscription = $em->getRepository('MainBundle:Inscription')->findOneBy(array('event'=>$idEvent,'userName'=>$this->getUser()->getUserName()));
        $user = $this->container->get('security.token_storage')->getToken()->getUser();


                if ($idEvent->getMaxNumber() > 0 && $inscription == null) {
                    $idEvent->setMaxNumber($idEvent->getMaxNumber() - 1);
                    $inscriptionn->setEventName($idEvent->getIdEvent());
                    $inscriptionn->setUserName($this->getUser()->getUserName());
                    $inscriptionn->setEvent($idEvent);
                    $em->persist($idEvent);
                    $em->persist($inscriptionn);
                    $em->flush();
                    return $this->render('inscription/msg1.html.twig');
                } else {
                    return $this->render('inscription/msg2.html.twig');
                }

        return $this->render('inscription/msg4.html.twig');

    }


}
