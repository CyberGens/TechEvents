<?php

namespace UserBundle\Controller;

use UserBundle\Entity\BlackList;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Blacklist controller.
 *
 */
class BlackListController extends Controller
{
    /**
     * Lists all blackList entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $blackLists = $em->getRepository('UserBundle:BlackList')->findAll();

        return $this->render('blacklist/index.html.twig', array(
            'blackLists' => $blackLists,
        ));
    }

    /**
     * Creates a new blackList entity.
     *
     */
    public function newAction(Request $request)
    {
        $blackList = new Blacklist();
        $form = $this->createForm('UserBundle\Form\BlackListType', $blackList);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($blackList);
            $em->flush();

            return $this->redirectToRoute('blacklist_show', array('idEntry' => $blackList->getIdentry()));
        }

        return $this->render('blacklist/new.html.twig', array(
            'blackList' => $blackList,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a blackList entity.
     *
     */
    public function showAction(BlackList $blackList)
    {
        $deleteForm = $this->createDeleteForm($blackList);

        return $this->render('blacklist/show.html.twig', array(
            'blackList' => $blackList,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing blackList entity.
     *
     */
    public function editAction(Request $request, BlackList $blackList)
    {
        $deleteForm = $this->createDeleteForm($blackList);
        $editForm = $this->createForm('UserBundle\Form\BlackListType', $blackList);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('blacklist_edit', array('idEntry' => $blackList->getIdentry()));
        }

        return $this->render('blacklist/edit.html.twig', array(
            'blackList' => $blackList,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a blackList entity.
     *
     */
    public function deleteAction(Request $request, BlackList $blackList)
    {
        $form = $this->createDeleteForm($blackList);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($blackList);
            $em->flush();
        }

        return $this->redirectToRoute('blacklist_index');
    }

    /**
     * Creates a form to delete a blackList entity.
     *
     * @param BlackList $blackList The blackList entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(BlackList $blackList)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('blacklist_delete', array('idEntry' => $blackList->getIdentry())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
