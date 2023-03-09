<?php

namespace App\Form;

use App\Entity\Don;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class DonType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('donneur')
            ->add('tel')
            
            ->add('type_don',ChoiceType::class, [
                'choices' => [
                    'vetement' => 'vetement',
                    ' nouriture' => 'nouriture',
                    'argent' => 'argent',
                    'livre' => 'livre',
                   
                ]])
            ->add('lieu')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Don::class,
        ]);
    }
}
