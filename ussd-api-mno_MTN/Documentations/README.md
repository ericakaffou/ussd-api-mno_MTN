# ussd-api-mno
18/10/2026
     securisation de jwt :   

            -mettre en place un refresh token
            -externaliser le jwt-secret dans database.properties et renommer ce dernier
19/10/2026

   * Securisation inter app ( app1, app2 et app3) à faire avec jwtoken auto generé  : à faire after , pas urgent (url whatapp)

    * Securisation app3 <-> mno  :   sécurisé :  jwtoken auto generé mno : TODO
                                     non sécurisé : url ouverte 


   callBackUrl : initialement j'avais le callbackurl dans routeMno car en cas de service forwarded vu que chaque mno à un model de donnée diffrent , on pourait avoir une url differente pour chaque MNO

   mais maintenant l'app est structurée ne 3 parties , meme si le UssdService est forwarded , app3 ne traite pas le service , donc les requetes devront toujours etre transferées à l'app2 du service concerné.
   du coup avoir le callBackurl dans Service est préferable!!!!
   ( pour ne pas tout remodeler , creer une new propriété app2Url dans UssdService)

   ???? mais aussi pour un service forwarded ... on peut se passer d'app2 et transferer depuis app3

   => choix : * avoir une proprieté app2Url non null pour les service non forwarded
              * garder callBackUrl dans RouteMno -> une url de callback par operateur à envoyé au client (third party) 