<?php

namespace ClientBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;




use Symfony\Bridge\Doctrine\Form\Type\EntityType;


use Symfony\Component\HttpFoundation\File\UploadedFile;

use ClientBundle\Entity\Event;
use ClientBundle\ClientBundle;
use Symfony\Component\Form\Extension\Core\Type\NumberType;


use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\FormTypeInterface;

class BlogPostType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('title', texttype::class,array('label'=>'Titre'))
            ->add('body' , texttype::class,array('label'=>'description'))
            ->add('Create Post', SubmitType::class,array('label'=>'Ajouter Post','attr'=>array('class'=>'btn btn-primary')))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => 'ClientBundle\Entity\BlogPost',
        ]);
    }

    public function getName()
    {
        return 'blog_post';
    }
}