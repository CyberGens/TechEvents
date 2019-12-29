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
class ReservationController extends Controller
{
    /**
     * Lists all reservation entities.
     *
     */
    public function indexAction(Request $request)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('LocataireBundle:Reservation')->findBy(array('idOwner'=>$this->getUser()->getId()));

        return $this->render('@Locataire/reservation/index.html.twig', array(
            'reservations' => $reservations,
            'path'=> $request->getUri()
        ));
    }
    public function lindexAction(Request $request,Local $local)
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
            'path'=> $request->getUri()
        ));
    }
    public function uindexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('LocataireBundle:Reservation')->findBy(array('idUser'=>$this->getUser()->getId()));

        return $this->render('@Locataire/reservation/index.html.twig', array(
            'reservations' => $reservations,
            'path'=> $request->getUri(),
        ));
    }
    /**
     * Creates a new reservation entity.
     * @param Request $request
     * @param Local $local
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|\Symfony\Component\HttpFoundation\Response
     */
    public function newAction(Request $request, Local $local)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $reservation = new Reservation();
        $form = $this->createForm('LocataireBundle\Form\ReservationType', $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reservation->setIdLocal($local);
            $data = $this->getDoctrine()->getManager()->getRepository('LocataireBundle:Reservation')->findByRI($reservation);
            if(($data==null))
            {
                    $em = $this->getDoctrine()->getManager();
                    $user = $em->getRepository('UserBundle:User')->findOneBy(array('id' => $local->getIdUser()));
                    $reservation->setIdLocal($local);
                    $reservation->setIdOwner($user);
                    $reservation->setIdUser($this->getUser());
                    $em->persist($reservation);
                    $em->flush();

                    return $this->redirectToRoute('reservation_show', array('idReservation' => $reservation->getIdReservation()));
                }
                else{ return $this->render('@Locataire/booked.html.twig', array('local'=>$local));}
                }

        return $this->render('@Locataire/reservation/new.html.twig', array(
            'reservation' => $reservation,
            'form' => $form->createView(),
            'path'=>$request->getUri()
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
        $interval = date_diff($reservation->getDateDebut(), $reservation->getDateFin());
        return $this->render('@Locataire/reservation/show.html.twig', array(
            'reservation' => $reservation,
            'delete_form' => $deleteForm->createView(),
            'path'=>$request->getUri(),
            'activeuser'=>$this->getUser(),
            'nodays'=>$interval->days+1,
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
            $data = $this->getDoctrine()->getManager()->getRepository('LocataireBundle:Reservation')->findByRI($reservation);
            if (($data == null)) {
                $this->getDoctrine()->getManager()->flush();

                $path = $request->getUri();
                if (strpos($path, '/locataire/local/') > 0)
                    return $this->redirectToRoute('lreservation_index', array('idLoc' => $reservation->getIdLocal()->getIdLoc()));
                elseif (strpos($path, '/locataire/reservation') > 0)
                    return $this->redirectToRoute('reservation_index');
                else
                    return $this->redirectToRoute('ureservation_index');

            }
            else return $this->render('@Locataire/booked.html.twig', array('local'=>$local));

        }

        return $this->render('@Locataire/reservation/edit.html.twig', array(
            'reservation' => $reservation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
            'path'=>$request->getUri()
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
        $path=$request->getUri();
        if(strpos($path,'/locataire/local/')>0)
            return $this->redirectToRoute('lreservation_index',array('idLoc'=>$reservation->getIdLocal()->getIdLoc()));
        elseif (strpos($path,'/locataire/reservation')>0)
            return $this->redirectToRoute('reservation_index');
        else
            return $this->redirectToRoute('ureservation_index');
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
