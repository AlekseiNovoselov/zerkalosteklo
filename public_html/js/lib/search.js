define([
    // Libs
    'jquery',
    // Deps
], function($){
    return function(event, callback, searchModel){
        console.log("ajax");
        event.preventDefault();
        var $btn = $(event.currentTarget);

        $.ajax({
            url: $('.search__form').attr("action"),
            data: {
                "q" : $('.search__query').val()
            },
            type: 'GET',
            beforeSend: function() {
                $btn.prop('disabled',true);
            },
            success: function(response) {
                searchModel.set({"response":response});
                callback(response, searchModel);
            },
            error: function(response) {
                this.alert.show(response.responseJSON["message"]);
            },
            complete: function() {
                $btn.prop('disabled',false);
            }
        })
    };
})