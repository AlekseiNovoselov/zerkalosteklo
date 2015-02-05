define([
    // Libs
    'backbone',
    // Views
    'main_view',
    'view_manager',
    // Model
    'user_model'
], function(Backbone, MainView, ViewManager, UserModel) {
    var Router = Backbone.Router.extend({
        routes: {
            '': 'mainAction'
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
            //this.toolbarView = new ToolbarView({model:this.model});
        }
    });
    return new Router();
})