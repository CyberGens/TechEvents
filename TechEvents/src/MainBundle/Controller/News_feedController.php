<?php

namespace MainBundle\Controller;

use MainBundle\Entity\News_feed;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * News_feed controller.
 *
 */
class News_feedController extends Controller
{
    /**
     * Lists all news_feed entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $news_feeds = $em->getRepository('MainBundle:News_feed')->findAll();

        return $this->render('news_feed/index.html.twig', array(
            'news_feeds' => $news_feeds,
        ));
    }

    /**
     * Creates a new news_feed entity.
     *
     */
    public function newAction(Request $request)
    {
        $news_feed = new News_feed();
        $form = $this->createForm('MainBundle\Form\News_feedType', $news_feed);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($news_feed);
            $em->flush();

            return $this->redirectToRoute('news_feed_show', array('idFeed' => $news_feed->getIdFeed()));
        }

        return $this->render('news_feed/new.html.twig', array(
            'news_feed' => $news_feed,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a news_feed entity.
     *
     */
    public function showAction(News_feed $news_feed)
    {
        $deleteForm = $this->createDeleteForm($news_feed);

        return $this->render('news_feed/show.html.twig', array(
            'news_feed' => $news_feed,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing news_feed entity.
     *
     */
    public function editAction(Request $request, News_feed $news_feed)
    {
        $deleteForm = $this->createDeleteForm($news_feed);
        $editForm = $this->createForm('MainBundle\Form\News_feedType', $news_feed);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('news_feed_edit', array('idFeed' => $news_feed->getIdFeed()));
        }

        return $this->render('news_feed/edit.html.twig', array(
            'news_feed' => $news_feed,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a news_feed entity.
     *
     */
    public function deleteAction(Request $request, News_feed $news_feed)
    {
        $form = $this->createDeleteForm($news_feed);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($news_feed);
            $em->flush();
        }

        return $this->redirectToRoute('news_feed_index');
    }

    /**
     * Creates a form to delete a news_feed entity.
     *
     * @param News_feed $news_feed The news_feed entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(News_feed $news_feed)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('news_feed_delete', array('idFeed' => $news_feed->getIdFeed())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
