<?php

namespace LocataireBundle\Controller;

use UserBundle\Entity\User;
use LocataireBundle\Entity\Reservation;
use LocataireBundle\Entity\Local;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Reservation controller.
 *
 */
class LocalReservationController extends Controller
{
    /**
     * Lists all reservation entities.
     *
     */
    public function indexAction(Request $request,Local $local)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('LocataireBundle:Reservation')->findBy(array('idLocal'=>$local->getIdLoc()));

        return $this->render('@Locataire/reservation/index.html.twig', array(
            'reservations' => $reservations,
            'isLocataire'=>strpos($request->getUri(),'/locataire')
        ));
    }


    /**
     * Finds and displays a reservation entity.
     *
     */
    public function showAction(Request $request,Reservation $reservation)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $deleteForm = $this->createDeleteForm($reservation);

        return $this->render('@Locataire/reservation/show.html.twig', array(
            'reservation' => $reservation,
            'delete_form' => $deleteForm->createView(),
            'path'=>$request->getUri()
        ));
    }

    /**
     * Displays a form to edit an existing reservation entity.
     *
     */
    public function editAction(Request $request, Reservation $reservation)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $deleteForm = $this->createDeleteForm($reservation);
        $editForm = $this->createForm('LocataireBundle\Form\ReservationType', $reservation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reservation_edit', array('idReservation' => $reservation->getIdreservation()));
        }

        return $this->render('@Locataire/reservation/edit.html.twig', array(
            'reservation' => $reservation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a reservation entity.
     *
     */
    public function deleteAction(Request $request, Reservation $reservation)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $form = $this->createDeleteForm($reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reservation);
            $em->flush();
        }

        return $this->redirectToRoute('reservation_index');
    }

    /**
     * Creates a form to delete a reservation entity.
     *
     * @param Reservation $reservation The reservation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reservation $reservation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('reservation_delete', array('idReservation' => $reservation->getIdreservation())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
