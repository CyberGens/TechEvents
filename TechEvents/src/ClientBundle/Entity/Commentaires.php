<?php

namespace ClientBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaires
 *
 * @ORM\Table(name="commentaires", indexes={@ORM\Index(name="idEvent", columns={"idEvent"}), @ORM\Index(name="iduse", columns={"iduse"})})
 * @ORM\Entity
 */
class Commentaires
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
     * @var integer
     *
     * @ORM\Column(name="idEvent", type="integer", nullable=true)
     */
    private $idevent;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User",cascade={"persist", "remove" })
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="iduse", referencedColumnName="id", onDelete="CASCADE")
     * })
     */
    private $iduse;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255, nullable=false)
     */
    private $contenu;

    /**
     * @var integer
     *
     * @ORM\Column(name="NbJaimes", type="integer", nullable=true)
     */
    private $nbjaimes;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     */
    private $date;



    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idevent
     *
     * @param integer $idevent
     *
     * @return Commentaires
     */
    public function setIdevent($idevent)
    {
        $this->idevent = $idevent;

        return $this;
    }

    /**
     * Get idevent
     *
     * @return integer
     */
    public function getIdevent()
    {
        return $this->idevent;
    }

    /**
     * Set iduse
     *
     * @param integer $iduse
     *
     * @return Commentaires
     */
    public function setIduse($iduse)
    {
        $this->iduse = $iduse;

        return $this;
    }

    /**
     * Get iduse
     *
     * @return integer
     */
    public function getIduse()
    {
        return $this->iduse;
    }

    /**
     * Set contenu
     *
     * @param string $contenu
     *
     * @return Commentaires
     */
    public function setContenu($contenu)
    {
        $this->contenu = $contenu;

        return $this;
    }

    /**
     * Get contenu
     *
     * @return string
     */
    public function getContenu()
    {
        return $this->contenu;
    }

    /**
     * Set nbjaimes
     *
     * @param integer $nbjaimes
     *
     * @return Commentaires
     */
    public function setNbjaimes($nbjaimes)
    {
        $this->nbjaimes = $nbjaimes;

        return $this;
    }

    /**
     * Get nbjaimes
     *
     * @return integer
     */
    public function getNbjaimes()
    {
        return $this->nbjaimes;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return Commentaires
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }
}
