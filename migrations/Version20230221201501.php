<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230221201501 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE besoin DROP FOREIGN KEY FK_8118E8111D518FD8');
        $this->addSql('DROP INDEX IDX_8118E8111D518FD8 ON besoin');
        $this->addSql('ALTER TABLE besoin DROP typebesoin_id');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE besoin ADD typebesoin_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE besoin ADD CONSTRAINT FK_8118E8111D518FD8 FOREIGN KEY (typebesoin_id) REFERENCES typebesoin (id)');
        $this->addSql('CREATE INDEX IDX_8118E8111D518FD8 ON besoin (typebesoin_id)');
    }
}
