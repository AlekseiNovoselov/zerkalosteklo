define([
    // Libs
    'jquery',
    'backbone',
    // Tmpl
    'search_result_tmpl'
], function($, Backbone, searchResultTmpl) {
    var ScoreboardView = Backbone.View.extend({
        template: searchResultTmpl,
        el: $('.search__result'),
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
    return ScoreboardView;
})