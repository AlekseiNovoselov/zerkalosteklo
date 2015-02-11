define([
    // Libs
    'backbone',
    // Views
    'main_view',
    'view_manager',
    // Model
    'user_model',
    'toolbar_view',
    'breadcrumbs_view',
    'gallery1_view',
    'gallery2_view',
    'search_model',
    "search_view",
    'justGallery',
], function(Backbone, MainView, ViewManager, UserModel, ToolbarView,
            BreadCrumbsView, Gallery1View, Gallery2View, SearchModel,
            SearchView, justGallery) {
    var Router = Backbone.Router.extend({
        routes: {
            //'': 'gallery1Action',
            '': 'mainAction',
            'gallery1' : 'gallery1Action',
            'gallery2' : 'gallery2Action',
            'search' : 'searchAction'
        },
        gallery1Action : function () {
            if (!this.gallery1View) {
                this.gallery1View = new Gallery1View();
                this.viewManager.addView(this.gallery1View)
            }
            this.gallery1View.show();
        },
        gallery2Action : function () {
            if (!this.gallery2View) {
                this.gallery2View = new Gallery2View();
                this.viewManager.addView(this.gallery2View)
            }
            this.gallery2View.show();
        },
        searchAction : function () {
            if (!this.searchView) {
                this.searchView = new SearchView();
                this.viewManager.addView(this.searchView)
            }
            this.searchView.show();
        },
        mainAction: function () {
            if (!this.mainView) {
                this.mainView = new MainView();
                this.viewManager.addView(this.mainView)
            }
            this.mainView.show();
        },
        initialize: function() {
            console.log("initialize");
            this.viewManager = new ViewManager();
            //this.model = new UserModel();
            this.searchModel = new SearchModel();
            // почему  название только model?
            this.toolbarView = new ToolbarView({model:this.searchModel});
            this.breadcrumbsView = new BreadCrumbsView();
            //this.Gallery = new justGallery();
        }
    });
    return new Router();
})