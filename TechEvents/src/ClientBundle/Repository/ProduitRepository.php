<?php

/**
 * Created by PhpStorm.
 * User: ASUS
 * Date: 03/01/2018
 * Time: 20:23
 */

namespace  AdminBundle\Repository;
use Doctrine\ORM\EntityRepository;


class ProduitRepository extends EntityRepository
{

    public function findProduitDQL($categorie)
    {
        $Query=$this->getEntityManager()
            ->createQuery(" 
 select v from ClientBundle:Produit v where v.nom LIKE :categorie OR v.categorie LIKE :categorie 

  ")
            ->setParameter('categorie','%'.$categorie.'%');


        return $Query->getResult();

    }

}