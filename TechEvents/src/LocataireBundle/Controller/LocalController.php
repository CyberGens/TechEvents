<?php

namespace LocataireBundle\Controller;

use LocataireBundle\Entity\Local;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Local controller.
 *
 */
class LocalController extends Controller
{
    /**
     * Lists all local entities.
     *
     */
    public function indexAction(Request $request )
    {

        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }

        else{
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $locals = $em->getRepository('LocataireBundle:Local')->findBy(array('idUser'=>$user->getId()));

            return $this->render('@Locataire/local/index.html.twig', array(
                'locals' => $locals, 'activeuser' => $this->getUser(),
                'isUser'=>strpos($request->getUri(),'/locaux')
            ));
        }


    }

    /**
     * Creates a new local entity.
     *
     */
    public function newAction(Request $request)
    {
        $currentUrl = $request->getUri();
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $local = new Local();
        $form = $this->createForm('LocataireBundle\Form\LocalType', $local);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $brochureFile = $form['img']->getData();
            if ($brochureFile) {
                $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
                $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();
                try {
                    $brochureFile->move(
                        $this->getParameter('images_directory'),
                        $newFilename
                    );
                    $local->setImg($newFilename);
                } catch (FileException $e) {
                }

            }
            $em = $this->getDoctrine()->getManager();
            $local->setIdUser($this->getUser());

            $em->persist($local);
            $em->flush();

            return $this->redirectToRoute('local_show', array('idLoc' => $local->getIdloc(),
                'isUser'=>strpos($request->getUri(),'/locaux')
                ));
        }

        return $this->render('@Locataire/local/new.html.twig', array(
            'local' => $local,
            'form' => $form->createView(),
            'activeid'=>$this->getUser()->getId()
        ));
    }

    /**
     * Finds and displays a local entity.
     *
     */
    public function showAction(Request $request,Local $local)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $deleteForm = $this->createDeleteForm($local);
        $em = $this->getDoctrine()->getManager();
        $owner = $em->getRepository('LocataireBundle:Local')->findOneBy(array('idUser'=>$local->getIdUser()));

        return $this->render('@Locataire/local/show.html.twig', array(
            'local' => $local,
            'delete_form' => $deleteForm->createView(),
            'activeuser'=>$this->getUser(),
            'isUser'=>strpos($request->getUri(),'/locaux'),
            'owner'=>$owner
        ));
    }

    /**
     * Displays a form to edit an existing local entity.
     *
     */
    public function editAction(Request $request, Local $local)
    {
        $currentUrl = $request->getUri();
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $deleteForm = $this->createDeleteForm($local);
        $editForm = $this->createForm('LocataireBundle\Form\LocalType', $local);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $brochureFile = $editForm['img']->getData();
            if ($brochureFile) {
                $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
                $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();
                try {
                    $brochureFile->move(
                        $this->getParameter('images_directory'),
                        $newFilename
                    );
                    $local->setImg($newFilename);
                } catch (FileException $e) {
                }

            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('local_show', array('idLoc' => $local->getIdloc()));
        }

        return $this->render('@Locataire/local/edit.html.twig', array(
            'local' => $local,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),

        ));
    }
    public function mapAction(Local $local)
    {
        return $this->render('@Locataire/localisation.html.twig', array(
            'local' => $local));

    }


    /**
     * Deletes a local entity.
     *
     */
    public function deleteAction(Request $request, Local $local)
    {
        if( $this->getUser()==null ){
            return $this->render('@Locataire/Register.html.twig', array('url'=>$request->getUri()));
        }

        else if(!in_array('ROLE_LOCATAIRE', $this->getUser()->getRoles())){
            return $this->render('@Locataire/AccessDenied.html.twig',array('url'=>$request->getUri()));
        }
        $form = $this->createDeleteForm($local);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($local);
            $em->flush();
        }

        return $this->redirectToRoute('local_index',array('isUser'=>strpos($request->getUri(),'/locaux')));
    }

    /**
     * Creates a form to delete a local entity.
     *
     * @param Local $local The local entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Local $local)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('local_delete', array('idLoc' => $local->getIdloc())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
