<?php

namespace UserBundle\Controller;

use UserBundle\Entity\SponsorFile;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Sponsorfile controller.
 *
 */
class SponsorFileController extends Controller
{
    /**
     * Lists all sponsorFile entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $sponsorFiles = $em->getRepository('UserBundle:SponsorFile')->findAll();

        return $this->render('@User/sponsorfile/index.html.twig', array(
            'sponsorFiles' => $sponsorFiles,
        ));
    }

    /**
     * Creates a new sponsorFile entity.
     *
     */
    public function newAction(Request $request)
    {
        $sponsorFile = new Sponsorfile();
        $form = $this->createForm('UserBundle\Form\SponsorFileType', $sponsorFile);
        $form->handleRequest($request);
        $user = $this->container->get('security.token_storage')->getToken()->getUser();

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $sponsorFile->setIdUser($user);
            $em->persist($sponsorFile);
            $em->flush();

            return $this->redirectToRoute('sponsorfile_show', array('idFile' => $sponsorFile->getIdfile()));
        }

        return $this->render('@User/sponsorfile/new.html.twig', array(
            'sponsorFile' => $sponsorFile,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a sponsorFile entity.
     *
     */
    public function showAction(SponsorFile $sponsorFile)
    {

        $deleteForm = $this->createDeleteForm($sponsorFile);

        return $this->render('@User/sponsorfile/show.html.twig', array(
            'sponsorFile' => $sponsorFile,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function showMineAction()
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $sponsorFile=$this->getDoctrine()->getRepository('UserBundle:SponsorFile')->findBy(['idUser' => $user])[0];
        $deleteForm = $this->createDeleteForm($sponsorFile);

        return $this->render('@User/sponsorfile/show.html.twig', array(
            'sponsorFile' => $sponsorFile,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing sponsorFile entity.
     *
     */
    public function editAction(Request $request, SponsorFile $sponsorFile)
    {
        $deleteForm = $this->createDeleteForm($sponsorFile);
        $editForm = $this->createForm('UserBundle\Form\SponsorFileType', $sponsorFile);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('sponsorfile_edit', array('idFile' => $sponsorFile->getIdfile()));
        }

        return $this->render('@User/sponsorfile/edit.html.twig', array(
            'sponsorFile' => $sponsorFile,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a sponsorFile entity.
     *
     */
    public function deleteAction(Request $request, SponsorFile $sponsorFile)
    {
        $form = $this->createDeleteForm($sponsorFile);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($sponsorFile);
            $em->flush();
        }

        return $this->redirectToRoute('sponsorfile_index');
    }

    public function acceptAction(Request $request, SponsorFile $sponsorFile)
    {
            $em = $this->getDoctrine()->getManager();
            $sponsorFile->setStatus("Accepted");
            $user = $sponsorFile->getIdUser();
            $user->setRole($sponsorFile->getType());
            $em->flush([$sponsorFile,$user]);

        return $this->redirectToRoute('sponsorfile_index');
    }

    public function refuseAction(Request $request, SponsorFile $sponsorFile)
    {
        $em = $this->getDoctrine()->getManager();
        $sponsorFile->setStatus("Refused");
        $em->flush($sponsorFile);

        return $this->redirectToRoute('sponsorfile_index');
    }

    /**
     * Creates a form to delete a sponsorFile entity.
     *
     * @param SponsorFile $sponsorFile The sponsorFile entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(SponsorFile $sponsorFile)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('sponsorfile_delete', array('idFile' => $sponsorFile->getIdfile())))
            ->setMethod('DELETE')
            ->getForm();
    }
}
