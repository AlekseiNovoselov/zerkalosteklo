define([
	// Libs
	'jquery',
	'backbone',
	// Tmpl
	'toolbar_guest_tmpl',
	'toolbar_user_tmpl',
	// Models
	'user_model',
    'search',
], function($, Backbone, toolbar_guest_tmpl, toolbar_user_tmpl, UserModel, Search) {
	var ToolbarView = Backbone.View.extend({
		template_user: toolbar_user_tmpl,
		template_guest: toolbar_guest_tmpl,
		el: $('.toolbar'),
		render: function() {
			//if( this.model.isLogin() ) {
			//	this.$el.html(this.template_user());
			//}
			//else {
				this.$el.html(this.template_guest());
			//}
		},
		initialize: function() {
            //this.listenTo(this.searchModel, 'change', this.)
			this.listenTo(this.model,'change', this.alert);
			this.render();
            //console.log(this.searchModel);
            console.log(this.model);
		},
        alert : function() {
            alert("response changed");
        },
        events: {
            "click .search__submit" : "search"
        },
        search : function(event) {
            //console.log(searchModel);
            window.location.hash = '#search';
            Search(event, this.callback, this.model);
            console.log(this.model);
        },
        callback : function (response, model) {
            console.log(model);
            //<a href="../upload/mashiny/mashiny_787_b.jpg" title="">
            //    <img alt="" src="../upload/mashiny/mashiny_787_m.jpg" />
            //</a>
            //this.model.getResponse();
            var res = response[0].name.split(".j");
            var str = "<a href="+"\"../upload/"+response[0].album+"/"+res[0]+"_m.jpg\""+ " title=\"\">"+ "\n" +
                "<img alt=\"\" src = \"../upload/"+response[0].album+"/"+res[0]+"_m.jpg\" />" + "\n" +
                "</a>";
            //var str = "<a href=";
            console.log(str);
            //console.log(response);
            //console.log(response[0].album + "/" + response[0].name);
            $( ".search__result" ).append(str);
        }
	});
	return ToolbarView;
})