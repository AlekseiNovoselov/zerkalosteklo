define([
    // Libs
    'jquery',
    'backbone',
    // Scripts
    'cropper',
    // Tmpl
    'main_guest_tmpl',
    'main_user_tmpl',
    'big_images_tmpl',
    'justGallery',

], function($, Backbone, Cropper, main_guest_tmpl, main_user_tmpl, big_images_tmpl, justGallery) {
    var MainView = Backbone.View.extend({
        template_user: main_user_tmpl,
        template_guest: main_guest_tmpl,
        template_big_images : big_images_tmpl,
        el: $('.main'),
        render: function() {
            this.$el.html(this.template_big_images());
        },
        /*crop : function() {
          this.Cropper.getResults();
        },*/
        show: function() {
            //this.Cropper = new Cropper();
            this.Gallery = new justGallery();
            this.trigger("showView",[ this ]);
            this.$el.delay(200).fadeIn(200);
        },
        hide: function() {
            this.$el.fadeOut(200);
        },
        initialize: function() {
            this.render();
        },
        /*events: {
            "click .lexaClass" : "crop"
        }*/
    });
    return MainView;
})