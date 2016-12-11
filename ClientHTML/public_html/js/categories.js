/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var Category = function (categorie) {
    this.ident = ko.observable(categorie.ident);
    this.nom = ko.observable(categorie.nom);
    this.description = ko.observable(categorie.description);
};

/* 
 Cette function est le controlleur de la vue  
 Elle assure la communication entre la vue et le modèle, une sorte de pont quoi!  
 */
var ViewModelCategorie = function (categories) {
    var self = this;
    //représente la liste des catégories  
    //La fonction prend la réponse obtenue du serveur en paramètre  
    //Ici nous supposons que vous avez chargé la liste des catégories  
    //ko.utils.arrayMap itère sur la collection et pour chaque objet trouvé, elle crée une instance de categorie   
    self.categories = ko.observableArray(ko.utils.arrayMap(categories, function (categorie) {
        return new Category(categorie);
    }));

    self.add = function (categorie) {
        if (categorie.nom !== "" && categorie.description !== "") {
            $.ajax({
                url: 'http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.categorie/',
                cache: false,
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                data: ko.toJSON(categorie)
            })
                    .success(function (data, status, jq) {
                        self.categories.push(new Category(categorie));
                        self.nom = "";
                        self.description = "";
                    })
                    .error(function (jq, status, error) {
                        $(".error").text(JSON.stringify(status + " " + error));
                    });
        }
    };

    self.remove = function (categorie) {
        self.categories.remove(categorie);
        $.ajax({
            url: "http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.categorie/" + categorie.ident(),
            type: "DELETE",
            contentType: "application/json",
            headers: {
                Accept: "application/json"
            }
        })
                .success(function (data, status, jq) {
                    getDataCategories();
                })
                .error(function (jq, status, error) {
                    $(".error").text(JSON.stringify(status + " " + error));
                });
    };

    self.update = function (categorie) {
        $.ajax({
            url: 'http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.categorie/' + categorie.ident(),
            cache: false,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            data: ko.toJSON(categorie)
        })
                .success(function (data, status, jq) {
                    //self.categories.replace();
                    $("#divdeps").dialog('open');
                })
                .error(function (jq, status, error) {
                    $(".error").text(JSON.stringify(status + " " + error));
                });
    };
};

var getDataCategories = function () {
    $.ajax({
        url: "http://localhost:8080/WS/webresources/fr.unice.miage.ntdp.bibliotheque.categorie",
        type: "GET",
        headers: {
            Accept: "application/json"
        }
    }).success(function (data, status, jq) {
        elem = $.get('categories.html').body;
        ko.applyBindings(new ViewModelCategorie(data), elem);
    }).error(function (jq, status, error) {
        $(".error").text(JSON.stringify(status + " " + error));

    });
};
