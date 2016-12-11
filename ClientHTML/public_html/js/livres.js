/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Livre = function (livre) {
    this.id = ko.observable(livre.id);
    this.isbn = ko.observable(livre.isbn);
    this.photo = ko.observable(livre.photo);
    this.publisheddate = ko.observable(livre.publisheddate);
    this.quantite = ko.observable(livre.quantite);
    this.resume = ko.observable(livre.resume);
    this.titre = ko.observable(livre.titre);
    this.categorieident = ko.observable(livre.categorie_ident);
    this.ecritpar = ko.observable(livre.ecrit_par_id);
};

/* 
 Cette function est le controlleur de la vue  
 Elle assure la communication entre la vue et le modèle, une sorte de pont quoi!  
 */
var isbinding = false;
var elem;
var ViewModelLivre = function (livres) {
    isbinding = true;
    var self = this;
    //représente la liste des catégories  
    //La fonction prend la réponse obtenue du serveur en paramètre  
    //Ici nous supposons que vous avez chargé la liste des catégories  
    //ko.utils.arrayMap itère sur la collection et pour chaque objet trouvé, elle crée une instance de livre   
    self.livres = ko.observableArray(ko.utils.arrayMap(livres, function (livre) {
        return new Livre(livre);
    }));

    self.add = function (livre) {
        if (livre.titre !== "" && livre.resume !== "") {
            $.ajax({
                url: 'http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.livre/',
                cache: false,
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: ko.toJSON(livre)
            })
                    .success(function (data, status, jq) {
                        self.livres.push(new Livre(livre));
                        self.nom = "";
                        self.description = "";
                    })
                    .error(function (jq, status, error) {
                        $(".error").text(JSON.stringify(status + " " + error));
                    });
        }
    };

    self.remove = function (livre) {
        self.livres.remove(livre);
        $.ajax({
            url: "http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.livre/" + livre.id(),
            type: "DELETE",
            contentType: "application/json",
            headers: {
                Accept: "application/json"
            }
        })
                .success(function (data, status, jq) {
                    getDataLivres();
                })
                .error(function (jq, status, error) {
                    $(".error").text(JSON.stringify(status + " " + error));
                });
    };

    self.update = function (livre) {
        $.ajax({
            url: 'http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.livre/' + livre.id(),
            cache: false,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: ko.toJSON(livre)
        })
                .success(function (data, status, jq) {
                    //self.livres.replace();
                })
                .error(function (jq, status, error) {
                    $(".error").text(JSON.stringify(status + " " + error));
                });
    };
};

var getDataLivres = function () {
    $.ajax({
        url: "http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.livre",
        type: "GET",
        headers: {
            Accept: "application/json"
        }
    }).success(function (data, status, jq) {
        //Cette fonction indique à knockout d'appliquer les données aux éléments de la page   
        //Elle est toujours appelée quand les données sont pretes et est appelée qu'une fois 
        if (!isbinding) {
            elem = $.get('livres.html').body;
            ko.applyBindings(new ViewModelLivre(data), elem);
        }
       
    }).error(function (jq, status, error) {
        $(".error").text(JSON.stringify(status + " " + error));

    });
};
