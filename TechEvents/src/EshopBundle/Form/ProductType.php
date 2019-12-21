<?php

namespace EshopBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class ProductType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            //->add('Category',EntityType::class,array(
                //'class'=>'EshopBundle\Entity\Product','choice_label'=>'category','multiple'=>false
            //))
            ->add('name')
           // ->add('file')
           ->add('file', FileType::class, array('label' => 'Image(JPG)'))
            ->add('price')
            ->add('description')
            ->add('quantity')
            ->add('category', EntityType::class,array('class'=>'EshopBundle:Category','choice_label'=>'category','multiple'=>false))
            ->add('ajouter',SubmitType::class)
            //->add('enregistrer',SubmitType::class)
        ;

    }


    /**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EshopBundle\Entity\Product'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eshopbundle_product';
    }


}