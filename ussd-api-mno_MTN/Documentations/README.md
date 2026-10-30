# ussd-api-mno
18/10/2026
     securisation de jwt :   

            -mettre en place un refresh token
            -externaliser le jwt-secret dans database.properties et renommer ce dernier
19/10/2026

   * Securisation inter app ( app1, app2 et app3) à faire avec jwtoken auto generé  : à faire after , pas urgent (url whatapp)

    * Securisation app3 <-> mno  :   sécurisé :  jwtoken auto generé mno : TODO
                                     non sécurisé : url ouverte 


   callBackUrl : initialement j'avais le callbackurl dans routeMno car en cas de service forwarded vu que chaque mno à un model de donnée diffrent , on pourait avoir une url differente pour chaque MNO  (1)

   mais maintenant l'app est structurée en 3 parties , meme si le UssdService est forwarded , app3 ne traite pas le service , donc les requetes devront toujours etre transferées à l'app2 du service concerné.

   du coup avoir le callBackurl dans Service est préferable!!!!  (2)
   ( pour ne pas tout remodeler , creer une new propriété app2Url dans UssdService)

   ???? mais aussi pour un service forwarded ... on peut se passer d'app2 et transferer depuis app3

   => choix : * avoir une proprieté app2Url  non null dans ussdService pour les service non forwarded

              * avoir  serviceForwardedUrl  dans ussdService -> une url de callback  à envoyé au client 


  mnoName et countryCode dans mnoService ???
  
  hypothese 1 : - j'ai deux mno intégrés ( mtn ci et mtn bn ) ayant la meme api 
              - 2 services ussd distincts ( en CI et au BN) les 2 avec le meme sc #122#
              - si j'ai une seule de app3 deployé comment identifer le menu à afficher ?????
                  dans la pullResquest xml il y a le msisdn dont les 3 premiers chiffre sont le countryCode !!!
                   ( on peut utiliser la meme app3)

  hypothese 2: j'ai deux mno intégrés du meme pays ayant la meme api   ???
                 ( on peut utiliser la meme app3)


                Solution actuelle : une autre instance de app3 pour gerer cela


   Solution générale : Une instance de app3 par operateur 



   CountryCode : il faut recuperer le countrycode de la meme facon de les ussdString car :
                 on peut tomber sur le cas ou nous somme connecté au groupe MTN , du coup la meme api peut servir plusieur pays.
                 dans ce cas on ne pourra identifier le pays qu'en recuperant le countrycode du msisdn

                 Version 1 : Le countryCode sera recuperé du msisdn ( 3 premiers caractères ; eventuellement parametrer cela dans les pRoperties)
                 (cela ne change rien au fait de Une instance de app3 par operateur  )




TODO : finaliser les services (NewRequestService , OldRequestService , PullRequestService ) . Faire la classe CleanRequestService