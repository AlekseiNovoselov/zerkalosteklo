define([
    // Libs
    'jquery',
    'backbone',
    // Deps
    'gallery2_tmpl',
    // Models
    'user_model',
    'justGallery',
], function($, Backbone, gallery2_tmpl, UserModel, justGallery) {
    var Gallery2View = Backbone.View.extend({
        template: gallery2_tmpl,
        el: $('.gallery2').children('.swipeboxEx'),
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
    return Gallery2View;
})