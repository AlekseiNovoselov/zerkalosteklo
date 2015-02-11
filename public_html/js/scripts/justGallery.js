define([
    // Libs
    'jquery',
    'backbone',
    'justifiedGallery',
    'swipebox'
], function($, Backbone, justifiedGallery, swipebox){ // variables
    var Gallery = Backbone.View.extend({

        initialize : function(gal) {
            $(document).ready(function () {
                //console.log($('.main').children('.swipeboxEx'));
                //console.log(elem);
                console.log("initialize justified Gallery");
                $('.swipeboxEx').each(function (i, el) {
                    console.log(i, el);
                    $(el).justifiedGallery({rel: 'gal' + i}).on('jg.complete', function () {
                        //if (i == 1)
                        $('.swipeboxEx a').swipebox(); //swipebox, wants to be called only once to work properly
                    });
                });
            });
        }
    });
    return Gallery;
});