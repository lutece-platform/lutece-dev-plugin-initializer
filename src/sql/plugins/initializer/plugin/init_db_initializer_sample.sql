--
-- Sample for category component 
--
TRUNCATE TABLE initializer_category_component;
TRUNCATE TABLE initializer_starter_component;

--
-- Sample for category component 
--
INSERT INTO initializer_category_component ( code, label_key, label_default) VALUES 
( 'search', 'initializer.categoryComponent.search.label','Recherche'),
( 'content_management', 'initializer.categoryComponent.content_management.label','Gestion de contenus'),
( 'communication', 'initializer.categoryComponent.communication.label','Interactions clients/administrateurs'),
( 'technical_services', 'initializer.categoryComponent.technical_services.label','Services techniques'),
( 'extensions', 'initializer.categoryComponent.extensions.label','Extensions de contenus'),
( 'authentication', 'initializer.categoryComponent.authentication.label','Authentification');

INSERT INTO initializer_starter_component ( artifact_id, code_category, label_key, label_default, description_key, description_default ) VALUES
('module-mylutece-database','authentication', 'initializer.starterComponent.module.mylutece.database.label','MyLutece Database','initializer.starterComponent.module.mylutece.database.description','Authentification Front Office grâce à une base de données locale'),
('plugin-blog','content_management','initializer.starterComponent.plugin.blog.label','Blog','initializer.starterComponent.plugin.blog.description','Creation de contenus grâce à des composants riches (textes riches,images, videos, cartes)'),
('plugin-document','content_management','initializer.starterComponent.plugin.document.label','Document','initializer.starterComponent.plugin.document.description','Creation de contenus par la configuration back office de documents'),
('plugin-seo','search','initializer.starterComponent.plugin.seo.label','SEO','initializer.starterComponent.plugin.seo.description','Optimisation de la détection de votre site par les moteurs de recherches'),
('library-lucene','search','initializer.starterComponent.library.lucene.label','Lucene','initializer.starterComponent.library.lucene.description','Recherche textuelle sur votre contenu au moyen du moteur de recherche Lucène'),
('plugin-forms','communication','initializer.starterComponent.plugin.forms.label','Forms','initializer.starterComponent.plugin.document.description','Creation de formulaires complexes par simple configuration'),
('plugin-searchstats','search','initializer.starterComponent.plugin.searchstats.label','SearchStats','initializer.starterComponent.plugin.searchstats.description','Statistiques sur les recherches de mots-clé sur votre site'),
('plugin-gtools','search','initializer.starterComponent.plugin.gtools.label','Document','initializer.starterComponent.plugin.gtools.description','Statistiques sur le trafic utilisateur de votre site au moyen de Google Analytics'),
('plugin-html','content_management','initializer.starterComponent.plugin.html.label','Billet HTML','initializer.starterComponent.plugin.html.description','Création de contenus par un editeur de texte riche HTML'),
('plugin-childpages','content_management','initializer.starterComponent.plugin.childpages.label','ChildPages','initializer.starterComponent.plugin.childpages.description','Composant graphique pour visualiser l\'arborescence de vos pages'),
('plugin-contact','communication','initializer.starterComponent.plugin.contact.label','Contact','initializer.starterComponent.plugin.contact.description','Creation d\'un formulaire de contact à destination des utilisateurs du site'),
('plugin-systeminfos','technical_services','initializer.starterComponent.plugin.systeminfos.label','SystemInfos','initializer.starterComponent.plugin.systeminfos.description','Outil d\'administration pour visualier les informations systèmes (RAM disponible, version de l\'OS ...)'),
('plugin-theme','technical_services','initializer.starterComponent.plugin.theme.label','Theme','initializer.starterComponent.plugin.theme.description','Gestion des différents thèmes de votre site'),
('module-extend-comment','extensions','initializer.starterComponent.module.extend.comment.label','ExtendComment','initializer.starterComponent.module.extend.comment.description','Extension de contenu sur vos ressources pour l\'écriture de commentaires de la part des utilisateurs'),
('module-extend-rating','extensions','initializer.starterComponent.module.extend.rating.label','ExtendRating','initializer.starterComponent.module.extend.rating.description','Extension de contenu pour permettre aux utilisateurs de voter pour les ressources de votre site'),
('module-extend-opengraph','extensions','initializer.starterComponent.module.extends.opengraph.label','ExtendOpengraph','initializer.starterComponent.module.extend.opengraph.description','Extension de contenu pour que vos utilisateurs puissent partager les ressources de votre site sur les réseaux sociaux'),
('plugin-sitelabels','content_management','initializer.starterComponent.plugin.sitelabels.label','SiteLabels','initializer.starterComponent.plugin.sitelabels.description','Gestion back office des labels de votre site'),
('plugin-avatar','authentication','initializer.starterComponent.plugin.avatar.label','Avatar','initializer.starterComponent.plugin.avatar.description','Gestion des avatars utilisateurs, basée sur le service Gravatar'),
('module-mylutece-oauth2','authentication','initializer.starterComponent.module.mylutece.oauth2.label','MyLutece Oauth2','initializer.starterComponent.module.mylutece.oauth2.description','Authentification Front Office au travers d\'un serveur Oauth2 / OpenidConnect'),
('module-mylutece-openam','authentication','initializer.starterComponent.module.mylutece.openam.label','MyLutece OpenAM','initializer.starterComponent.module.mylutece.openam.description','Authentification Front Office au travers d\'un serveur OpenAM'),
('module-mylutece-wssodatabase','authentication','initializer.starterComponent.module.mylutece.wssodatabase.label','MyLutece WSSO Front','initializer.starterComponent.module.mylutece.wssodatabase.description','Authentification Front Office au travers d\'un serveur WSSO'),
('plugin-adminauthenticationwsso','authentication','initializer.starterComponent.plugin.adminauthenticationwsso.label','WSSO Back','initializer.starterComponent.plugin.adminauthenticationwsso.description','Authentification Back Office au travers d\'un serveur WSSO'),
('module-mylutece-cas','authentication','initializer.starterComponent.module.mylutece.cas.label','MyLutece CAS','initializer.starterComponent.module.mylutece.cas.description','Authentification Front Office au travers d\'un serveur CAS'),
('plugin-piwik','technical_services','initializer.starterComponent.plugin.piwik.label','Piwik','initializer.starterComponent.plugin.piwik.description','Statistiques sur le trafic de votre site grâce à Piwik'),
('plugin-workflow','technical_services','initializer.starterComponent.plugin.workflow.label','Workflow','initializer.starterComponent.plugin.workflow.description','Moteur de workflow pour les ressources de votre site');
