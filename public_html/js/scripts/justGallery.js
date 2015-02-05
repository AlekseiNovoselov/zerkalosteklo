define([
    // Libs
    'jquery',
    'backbone',
    'justifiedGallery',
    'swipebox'
], function($, Backbone, justifiedGallery, swipebox){ // variables
    var Gallery = Backbone.View.extend({

        initialize: function() {
            $(document).ready(function () {
                $('.swipeboxEx').each(function (i, el) {
                    $(el).justifiedGallery({rel: 'gal' + i}).on('jg.complete', function () {
                        if (i == 1) $('.swipeboxEx a').swipebox(); //swipebox, wants to be called only once to work properly
                    });
                });
            });
        },
    });
    return Gallery;
});