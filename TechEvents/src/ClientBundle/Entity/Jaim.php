<?php

namespace ClientBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Jaim
 *
 * @ORM\Table(name="jaim", indexes={@ORM\Index(name="id_client", columns={"id_client"}), @ORM\Index(name="idroduit", columns={"idroduit"})})
 * @ORM\Entity
 */
class Jaim
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User",cascade={"persist", "remove" })
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id", onDelete="CASCADE")
     * })
     */
    private $idClient;

    /**
     * @var \Commentaires
     *
     * @ORM\ManyToOne(targetEntity="Commentaires")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idroduit", referencedColumnName="id", onDelete="CASCADE")
     * })
     */
    private $idroduit;

    /**
     * @var integer
     *
     * @ORM\Column(name="etat", type="integer", nullable=false)
     */
    private $etat;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     * @return Jaim
     */
    public function setId($id)
    {
        $this->id = $id;
        return $this;
    }

    /**
     * @return int
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * @param int $idClient
     * @return Jaim
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;
        return $this;
    }

    /**
     * @return int
     */
    public function getIdroduit()
    {
        return $this->idroduit;
    }

    /**
     * @param int $idroduit
     * @return Jaim
     */
    public function setIdroduit($idroduit)
    {
        $this->idroduit = $idroduit;
        return $this;
    }

    /**
     * @return int
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param int $etat
     * @return Jaim
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
        return $this;
    }



}
