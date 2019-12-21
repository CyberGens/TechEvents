<?php


namespace EshopBundle\Repository;
class ProductRepository extends \Doctrine\ORM\EntityRepository{

    public function myfindAll()
    {
        $query=$this->getEntityManager()
            ->createQuery("select c from EshopBundle:Category c");
        return $query->getResult();
    }


}