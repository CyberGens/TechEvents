<?php


namespace EshopBundle\Entity;
use Doctrine\ORM\Mapping as ORM;


/**
 * Category
 *
 * @ORM\Entity
 */

class Category
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="namecategorie", type="string", length=255,nullable=true)
     */
    private $namecategorie;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNamecategorie()
    {
        return $this->namecategorie;
    }

    /**
     * @param string $namecategorie
     */
    public function setNamecategorie($namecategorie)
    {
        $this->namecategorie = $namecategorie;
    }


    public function __get($category)
    {
        return $this->namecategorie;
    }

}