define([
    // Libs
    'jquery',
    'backbone',
    // Deps
    'gallery1_tmpl',
    // Models
    'user_model',
    'justGallery',
], function($, Backbone, gallery1_tmpl, UserModel, justGallery) {
    var Gallery1View = Backbone.View.extend({
        template: gallery1_tmpl,
        el: $('.gallery1').children('.swipeboxEx'),
        render: function() {
            this.$el.html(this.template());
            this.Gallery = new justGallery();
        },
        show: function() {
            this.trigger("showView",[ this ]);
            this.$el.delay(200).fadeIn(200);
        },
        hide: function() {
            this.$el.fadeOut(200);
        },
        initialize: function() {
            this.render();
        }
    });
    return Gallery1View;
})