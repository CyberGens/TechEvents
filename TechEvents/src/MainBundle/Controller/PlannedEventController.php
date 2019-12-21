<?php

namespace MainBundle\Controller;

use MainBundle\Entity\PlannedEvent;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Plannedevent controller.
 *
 */
class PlannedEventController extends Controller
{
    /**
     * Lists all plannedEvent entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $plannedEvents = $em->getRepository('MainBundle:PlannedEvent')->findAll();

        return $this->render('plannedevent/index.html.twig', array(
            'plannedEvents' => $plannedEvents,
        ));
    }

    /**
     * Creates a new plannedEvent entity.
     *
     */
    public function newAction(Request $request)
    {
        $plannedEvent = new Plannedevent();
        $form = $this->createForm('MainBundle\Form\PlannedEventType', $plannedEvent);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($plannedEvent);
            $em->flush();

            return $this->redirectToRoute('plannedevent_show', array('numPlan' => $plannedEvent->getNumPlan()));
        }

        return $this->render('plannedevent/new.html.twig', array(
            'plannedEvent' => $plannedEvent,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a plannedEvent entity.
     *
     */
    public function showAction(PlannedEvent $plannedEvent)
    {
        $deleteForm = $this->createDeleteForm($plannedEvent);

        return $this->render('plannedevent/show.html.twig', array(
            'plannedEvent' => $plannedEvent,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing plannedEvent entity.
     *
     */
    public function editAction(Request $request, PlannedEvent $plannedEvent)
    {
        $deleteForm = $this->createDeleteForm($plannedEvent);
        $editForm = $this->createForm('MainBundle\Form\PlannedEventType', $plannedEvent);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('plannedevent_edit', array('numPlan' => $plannedEvent->getNumPlan()));
        }

        return $this->render('plannedevent/edit.html.twig', array(
            'plannedEvent' => $plannedEvent,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a plannedEvent entity.
     *
     */
    public function deleteAction(Request $request, PlannedEvent $plannedEvent)
    {
        $form = $this->createDeleteForm($plannedEvent);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($plannedEvent);
            $em->flush();
        }

        return $this->redirectToRoute('plannedevent_index');
    }

    /**
     * Creates a form to delete a plannedEvent entity.
     *
     * @param PlannedEvent $plannedEvent The plannedEvent entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(PlannedEvent $plannedEvent)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('plannedevent_delete', array('numPlan' => $plannedEvent->getNumPlan())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
