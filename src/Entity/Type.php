<?php

namespace App\Entity;

use App\Repository\TypeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: TypeRepository::class)]
class Type
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $name = null;

    #[ORM\OneToMany(mappedBy: 'type', targetEntity: Besoin::class)]
    private Collection $besoin;

    public function __construct()
    {
        $this->besoin = new ArrayCollection();
    }

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

    /**
     * @return Collection<int, Besoin>
     */
    public function getBesoin(): Collection
    {
        return $this->besoin;
    }

    public function addBesoin(Besoin $besoin): self
    {
        if (!$this->besoin->contains($besoin)) {
            $this->besoin->add($besoin);
            $besoin->setType($this);
        }

        return $this;
    }

    public function removeBesoin(Besoin $besoin): self
    {
        if ($this->besoin->removeElement($besoin)) {
            // set the owning side to null (unless already changed)
            if ($besoin->getType() === $this) {
                $besoin->setType(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return $this->name; // Replace "name" with the property you want to use as the string representation of the object
    }

}
