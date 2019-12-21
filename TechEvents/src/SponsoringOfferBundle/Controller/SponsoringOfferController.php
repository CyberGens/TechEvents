<?php

namespace SponsoringOfferBundle\Controller;

use SponsoringOfferBundle\Entity\SponsoringOffer;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Sponsoringoffer controller.
 *
 * @Route("sponsoringoffer")
 */
class SponsoringOfferController extends Controller
{
    /**
     * Lists all sponsoringOffer entities.
     *
     * @Route("/", name="sponsoringoffer_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $sponsoringOffers = $em->getRepository('SponsoringOfferBundle:SponsoringOffer')->findAll();

        return $this->render('sponsoringoffer/index.html.twig', array(
            'sponsoringOffers' => $sponsoringOffers,
        ));
    }

    /**
     * Creates a new sponsoringOffer entity.
     *
     * @Route("/new", name="sponsoringoffer_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $sponsoringOffer = new Sponsoringoffer();
        $form = $this->createForm('SponsoringOfferBundle\Form\SponsoringOfferType', $sponsoringOffer);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($sponsoringOffer);
            $em->flush();

            return $this->redirectToRoute('sponsoringoffer_show', array('idOffer' => $sponsoringOffer->getIdoffer()));
        }

        return $this->render('sponsoringoffer/new.html.twig', array(
            'sponsoringOffer' => $sponsoringOffer,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a sponsoringOffer entity.
     *
     * @Route("/{idOffer}", name="sponsoringoffer_show")
     * @Method("GET")
     */
    public function showAction(SponsoringOffer $sponsoringOffer)
    {
        $deleteForm = $this->createDeleteForm($sponsoringOffer);

        return $this->render('sponsoringoffer/show.html.twig', array(
            'sponsoringOffer' => $sponsoringOffer,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing sponsoringOffer entity.
     *
     * @Route("/{idOffer}/edit", name="sponsoringoffer_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, SponsoringOffer $sponsoringOffer)
    {
        $deleteForm = $this->createDeleteForm($sponsoringOffer);
        $editForm = $this->createForm('SponsoringOfferBundle\Form\SponsoringOfferType', $sponsoringOffer);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('sponsoringoffer_edit', array('idOffer' => $sponsoringOffer->getIdoffer()));
        }

        return $this->render('sponsoringoffer/edit.html.twig', array(
            'sponsoringOffer' => $sponsoringOffer,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a sponsoringOffer entity.
     *
     * @Route("/{idOffer}/delete", name="sponsoringoffer_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, SponsoringOffer $sponsoringOffer)
    {
        $form = $this->createDeleteForm($sponsoringOffer);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($sponsoringOffer);
            $em->flush();
        }

        return $this->redirectToRoute('sponsoringoffer_index');
    }

    /**
     * Creates a form to delete a sponsoringOffer entity.
     *
     * @param SponsoringOffer $sponsoringOffer The sponsoringOffer entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(SponsoringOffer $sponsoringOffer)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('sponsoringoffer_delete', array('idOffer' => $sponsoringOffer->getIdoffer())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


    public function rechercheAction(Request $request)
    {
        $description=$request->get('description');
        if(isset($description) && !empty($description)){
            $em=$this->getDoctrine();
            $club=$em->getRepository(SponsoringOffer::class)
                ->rechercheDescription($description);
            return $this->render('SponsoringOffer/sponsoringoffer_show.html.twig',
                array('clubs'=>$club));
        }
        return $this->render('SponsoringOffer/sponsoringoffer_index.html.twig');


    }






}
