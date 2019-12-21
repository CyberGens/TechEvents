<?php

namespace LocataireBundle\Controller;

use LocataireBundle\Entity\Local;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class UserLocalController extends Controller
{
    public function indexAction(Request $request)
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();


            $locals = $em->getRepository('LocataireBundle:Local')->findAll();

            return $this->render('@Locataire/local/index.html.twig', array(
                'locals' => $locals,'activeuser'=>$this->getUser(),
                'isUser'=>strpos($request->getUri(),'/locaux')
            ));

    }

    /**
     * Finds and displays a local entity.
     * @param Request $request
     * @param Local $local
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function showAction(Request $request,Local $local)
    {
        $deleteForm = $this->createDeleteForm($local);
        return $this->render('@Locataire/local/show.html.twig', array(
            'local' => $local,
            'delete_form' => $deleteForm->createView(),
            'activeuser'=>$this->getUser(),
            'isUser'=>strpos($request->getUri(),'/locaux')

        ));
    }
    private function createDeleteForm(Local $local)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('local_delete', array('idLoc' => $local->getIdloc())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }

}
