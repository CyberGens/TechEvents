<?php


namespace ClientBundle\Controller;


use ClientBundle\Entity\Mail;
use ClientBundle\Form\MailType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;


class MailController extends Controller
{
    public function MailAction(Request $request)
    {

        $mail = new Mail();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $id = $user->getId();
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("ClientBundle:User")->find($id);
        $Lignecommande = $em->getRepository("ClientBundle:Lignecommande")->TrierEvents();
        $form=  $this->createForm(MailType::class,  $mail);
        $form->handleRequest($request) ;
        if ($form->isValid()) {
            $message = \Swift_Message::newInstance()
                ->setSubject('Passation de la commande pour :')
                ->setFrom($user->getEmail(),$mail->getText())
                ->setTo('soukelmaadinaa@souk.tn')
                ->setBody(
                    $this->renderView(
                        '@Client/client/Email.html.twig'
                    ),
                    'text/html'
                );

            $this->get('mailer')->send($message);
            $manager = $this->get('mgilet.notification');
            $notif = $manager->createNotification(''.$user->getUsername().'vous a envoyé un email');
            $notif->setMessage('email ');
            $notif->setLink('http://symfony.com/');
            $manager->addNotification(array($this->getUser()), $notif, true);
            return $this->redirect($this->generateUrl('my_app_mail_accuse'));         }

        return $this->render('@ClientBundle/client/Mail.html.twig', array('form'=>$form->createView()));
    }


    public function successAction()
    {
        return new Response("email envoyé avec succès, Merci de mémoriser votre code :) .");
    }

    public function AddNotifAction($manager)
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $notif = $manager->createNotification('New Test');
        $notif->setMessage('Test');
        $notif->setLink('http://symfony.com/');
        $manager->addNotification(array($user), $notif, true);

        return $this->redirectToRoute('index');
    }

}
