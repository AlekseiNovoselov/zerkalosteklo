requirejs.config({
    baseUrl: '/js',
    paths:{
        // Libs
        "jquery" : "lib/jquery",
        "backbone" : "lib/backbone",
        "underscore" : "lib/underscore",
        "logout" : "lib/logout",
        "validate" : "lib/validate",
        "jquery.validate" : "lib/jquery.validate",
        "justifiedGallery" : "lib/justifiedGallery",
        "swipebox" : "lib/jquery.swipebox.min",
        // Templates/**
        "game_tmpl" : "tmpl/game_tmpl",
        "login_tmpl" : "tmpl/login_tmpl",
        "joystick_tmpl" : "tmpl/joystick_tmpl",
        "main_user_tmpl" : "tmpl/main_user_tmpl",
        "main_guest_tmpl" : "tmpl/main_guest_tmpl",
        "registration_tmpl" : "tmpl/registration_tmpl",
        "scoreboard_tmpl" : "tmpl/scoreboard_tmpl",
        "profile_tmpl" : "tmpl/profile_tmpl",
        "toolbar_user_tmpl" : "tmpl/toolbar_user_tmpl",
        "toolbar_guest_tmpl" : "tmpl/toolbar_guest_tmpl",
        "big_images_tmpl" : "tmpl/big_images_tmpl",
        // Router
        "router" : "router",
        // Views
        "main_view" : "views/main_view",
        "game_view" : "views/game_view",
        "login_view" : "views/login_view",
        "scoreboard_view" : "views/scoreboard_view",
        "registration_view" : "views/registration_view",
        "profile_view" : "views/profile_view",
        "alert_view" : "views/alert_view",
        "view_manager" : "views/view_manager",
        "toolbar_view" : "views/;toolbar_view",
        "canvas_view" : "views/canvas_view",
        "joystick_view" : "views/joystick_view",
        // Models
        "image_model" : "models/image_model",
        "user_model" : "models/user_model",
        "selection_model" : "models/selection_model",
        // Collections
        //Scripts
        "cropper" : "scripts/cropper",
        "justGallery" : "scripts/justGallery"
    },
    shim: {
        'jquery': {
            exports: '$'
        },
        'backbone' : {
            deps: ['jquery', 'underscore'],
            exports: 'Backbone'
        },
        'underscore' : {
            deps: ['jquery'],
            exports: '_'
        },
        'jquery.validate' : {
            deps: ['jquery']
        }
    }
});

requirejs([
    // Libs
    'backbone',
    // Deps
    'router'
], function(Backbone, Router) {
    Backbone.history.start();
})