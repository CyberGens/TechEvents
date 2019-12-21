<?php


namespace EshopBundle\Entity;
use Doctrine\ORM\Mapping as ORM;


/**
 * Panier
 *
 * @ORM\Entity
 */
class Panier
{/**
 * @var integer
 *
 * @ORM\Column(name="id", type="integer", nullable=false)
 * @ORM\Id
 * @ORM\GeneratedValue(strategy="IDENTITY")
 */
    private $id;

    /**
     * @var integer
     *
     * @ORM\Column(name="quantite", type="integer", nullable=true)
     */
    private $quantite;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=true)
     */
    private $prix;

    /**
     * @var float
     *
     * @ORM\Column(name="total", type="float", precision=10, scale=0, nullable=true)
     */
    private $total;
    /**
     * @var integer
     *
     * @ORM\Column(name="idUser", type="integer", nullable=true)
     */
    private $idUser;
    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Product",cascade={"persist"})
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idProd", referencedColumnName="id_product")
     * })
     */
    private $idProd;

    /**
     * @var string
     *
     * @ORM\Column(name="nameProd", type="string", length=255, nullable=false)
     */
    private $nameProd;

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
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param int $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }

    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return float
     */
    public function getTotal()
    {
        return $this->total;
    }

    /**
     * @param float $total
     */
    public function setTotal($total)
    {
        $this->total = $total;
    }

    /**
     * @return \Membre
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * @param \Membre $idUser
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;
    }

    /**
     * @return \Membre
     */
    public function getIdProd()
    {
        return $this->idProd;
    }

    /**
     * @param \Membre $idProd
     */
    public function setIdProd($idProd)
    {
        $this->idProd = $idProd;
    }

    /**
     * @return string
     */
    public function getNameProd()
    {
        return $this->nameProd;
    }

    /**
     * @param string $nameProd
     */
    public function setNameProd($nameProd)
    {
        $this->nameProd = $nameProd;
    }



}