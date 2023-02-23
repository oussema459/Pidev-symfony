<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230222113541 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE association ADD CONSTRAINT FK_FD8521CC294CCED FOREIGN KEY (category_type_id) REFERENCES category_type (id)');
        $this->addSql('CREATE INDEX IDX_FD8521CC294CCED ON association (category_type_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE association DROP FOREIGN KEY FK_FD8521CC294CCED');
        $this->addSql('DROP INDEX IDX_FD8521CC294CCED ON association');
    }
}
