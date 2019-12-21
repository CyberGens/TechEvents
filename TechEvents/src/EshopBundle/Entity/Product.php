<?php

namespace EshopBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Product
 *
 * @ORM\Table(name="product")
 * @ORM\Entity
 */
class Product
{



    /**
     * @var integer
     *
     * @ORM\Column(name="id_product", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idProduct;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255, nullable=false)
     */
    private $name;



    /**
     * @var float
     *
     * @ORM\Column(name="price", type="float", precision=10, scale=0, nullable=false)
     */
    private $price;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", nullable=false)
     */
    private $description;

    /**
     * @var integer
     *
     * @ORM\Column(name="quantity", type="integer", nullable=false)
     */
    private $quantity;
    /**
     * @ORM\Column( type="string" ,length=255, nullable=false)
     */
  // public $nomImage;
    /**
     * @ORM\Column(type="string")
     *
     * @Assert\NotBlank(message="Ajouter une image jpg")
     * @Assert\File(mimeTypes={ "image/jpeg" })
     */
    private $file;
    /**
     * @ORM\ManyToOne(targetEntity="Category")
     * @ORM\JoinColumn(name="category",referencedColumnName="id")
     */
    private $category;




    public function getid()
    {
        return $this->idProduct;
    }

    /**
     * @param int $idProduct
     */




    public function setid($idProduct)
    {
        $this->idProduct = $idProduct;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }



    /**
     * @return float
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @param float $price
     */
    public function setPrice($price)
    {
        $this->price = $price;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return int
     */
    public function getQuantity()
    {
        return $this->quantity;
    }

    /**
     * @param int $quantity
     */
    public function setQuantity($quantity)
    {
        $this->quantity = $quantity;
    }

    /**
     * @return int
     */
 // public function getWebPath(){
      // return null===$this->nomImage?null:$this->getUploadDir.'/'.$this->nomImage;

  // }
  // protected function getUploadRootDir(){
       //return '__DIR__./../../../../web/'.$this->getUploadDir();
  // }
  // protected function getUploadDir()
   //{
       //return 'images';

   //}
   //public function uploadProfilePicture(){
       //$this->file->move($this->getUploadRootDir(),$this->file->getClientOriginalName());
           //$this->nomImage=$this->file->getClientOriginalName();
          // $this->file=null;


  // }

    /**
     * Set nomImage
     * @param string $nomImage
     * @return Category
     */
   //public function  setNomImage($nomImage)
 // {
    // $this->nomImage = $nomImage;
     // return $this;
  // }


    /**
     * Get nomImage
     * @return string
     */
   //public function getNomImage(){
       //return $this->nomImage;
  //}

    /**
     * @return mixed
     */
    public function getFile()
    {
        return $this->file;
    }

    /**
     * @param mixed $file
     */
    public function setFile($file)
    {
        $this->file = $file;
        return $this;
    }

    /**
     * @return mixed
     */
    public function getCategory()
    {
        return $this->category;
    }

    /**
     * @param mixed $category
     */
    public function setCategory(\EshopBundle\Entity\Category $category = null)
    {
        $this->category = $category;
    }








}

