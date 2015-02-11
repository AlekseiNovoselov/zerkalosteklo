define([
    // Libs
    'jquery',
    'backbone',
    // Tmpl
    'breadcrumbs_tmpl'
], function($, Backbone, BreadCrumbs_Tmpl) {
    var BreadCrumbsView = Backbone.View.extend({
        template: BreadCrumbs_Tmpl,
        el: $('.breadcrumbs'),
        render: function() {
            this.$el.html(this.template());
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
    return BreadCrumbsView;
})