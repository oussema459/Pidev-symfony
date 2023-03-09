<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\BesoinRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serialisez\Annotation\Groups;

#[ORM\Entity(repositoryClass: BesoinRepository::class)]
class Besoin
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    #[Groups("besoin")]
    private ?int $id = null;
   
    
    #[ORM\Column(length: 255)]
    #[Groups("besoin")]

    private ?string $name = null;

    #[ORM\Column(length: 255)]
    #[Groups("besoin")]
    private ?string $msg = null;


    
    
    

    #[ORM\Column(length: 255)]
    #[Groups("besoin")]
    private ?string $association = null;

    #[ORM\Column(length: 255)]
    #[Groups("besoin")]
    private ?string $lieu = null;

    #[ORM\Column(length: 255,nullable: true)]
    #[Groups("besoin")]
   
    /**
     * @Assert\File(
     *     mimeTypes={"image/png", "image/jpeg", "image/gif"},
     *     mimeTypesMessage="Veuillez télécharger une image valide (png, jpeg, gif)"
     * )
     */
    private ?string $image = null;

    #[ORM\ManyToOne(inversedBy: 'besoin')]
    #[Groups("besoin")]
    private ?Type $type = null;



    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getMsg(): ?string
    {
        return $this->msg;
    }

    public function setMsg(string $msg): self
    {
        $this->msg = $msg;

        return $this;
    }

  
    public function getAssociation(): ?string
    {
        return $this->association;
    }

    public function setAssociation(string $association): self
    {
        $this->association = $association;

        return $this;
    }

    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getType(): ?Type
    {
        return $this->type;
    }

    public function setType(?Type $type): self
    {
        $this->type = $type;

        return $this;
    }
    public function __toString()
    {
        return $this->name; // Replace "name" with the property you want to use as the string representation of the object
    }
    
 
}
