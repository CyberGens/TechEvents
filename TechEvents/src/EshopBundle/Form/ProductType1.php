<?php

namespace EshopBundle\Form;

use Symfony\Component\Form\AbstractType;

use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\HttpFoundation\File\File ;

class ProductType1 extends AbstractType
{

    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('name')
            //->add('file', FileType::class, array('label' => 'Image(JPG)'))
            ->add('file', FileType::class, array('data_class' => null,'required' => false))
            ->add('price')
            ->add('description')
            ->add('quantity')
            //->add('ajouter',SubmitType::class)
            ->add('enregistrer',SubmitType::class)
        ;

    }



    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EshopBundle\Entity\Product'
        ));
    }


    public function getBlockPrefix()
    {
        return 'eshopbundle_product';
    }


}