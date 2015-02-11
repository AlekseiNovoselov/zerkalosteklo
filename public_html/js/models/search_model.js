define([
    // Libs
    'backbone'
], function(Backbone) {
    var UserModel = Backbone.Model.extend({
        defaults: {
            response : ""
        },
        resetModel: function() {
            this.set({"response":""});
        },
        getResponse : function() {
            console.log("get Response");
            console.log(this.response);
        },
        initialize: function() {
            //this.fetch();
        }
    });
    return UserModel;
})