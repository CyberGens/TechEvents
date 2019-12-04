<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SponsorFile
 *
 * @ORM\Table(name="sponsor_file", indexes={@ORM\Index(name="FK_User_File", columns={"Id_user"})})
 * @ORM\Entity
 */
class SponsorFile
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id_file", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    protected $idFile;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=20, nullable=false)
     */
    protected $type;

    /**
     * @var string
     *
     * @ORM\Column(name="URL", type="string", length=255, nullable=false)
     */
    protected $url;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="text", length=65535, nullable=false)
     */
    protected $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Status", type="string", length=20, nullable=false)
     */
    protected $status = 'Untreated';

    /**
     * @var \User
     *
     * @ORM\OneToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="Id_user", referencedColumnName="id")
     * })
     */
    protected $idUser;


/**
     * Get idFile
     *
     * @return integer
     */
    public function getIdFile()
    {
        return $this->idFile;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return SponsorFile
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set url
     *
     * @param string $url
     *
     * @return SponsorFile
     */
    public function setUrl($url)
    {
        $this->url = $url;

        return $this;
    }

    /**
     * Get url
     *
     * @return string
     */
    public function getUrl()
    {
        return $this->url;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return SponsorFile
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set status
     *
     * @param string $status
     *
     * @return SponsorFile
     */
    public function setStatus($status)
    {
        $this->status = $status;

        return $this;
    }

    /**
     * Get status
     *
     * @return string
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * Set idUser
     *
     * @param \UserBundle\Entity\User $idUser
     *
     * @return SponsorFile
     */
    public function setIdUser(\UserBundle\Entity\User $idUser = null)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return \UserBundle\Entity\User
     */
    public function getIdUser()
    {
        return $this->idUser;
    }
}
