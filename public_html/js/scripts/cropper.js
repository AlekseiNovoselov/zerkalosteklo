define([
    // Libs
    'jquery',
    'backbone',
    'selection_model',
    'image_model'
], function($, Backbone, selectionModel, imageModel){ // variables
    var Cropper = Backbone.View.extend({

        changeCanvasSize : function() {
            console.log(this.imageModel.imageWidthInCanvas);
            var canvas = document.getElementById('panel');
            canvas.width = this.imageModel.imageWidthInCanvas;
            canvas.height = this.imageModel.imageHeightInCanvas;
        },

        drawScene : function() {
            var canvas = document.getElementById('panel');
            var ctx = canvas.getContext('2d');
            ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height); // clear canvas
            // and make it darker
            ctx.drawImage(this.imageModel.image, 0, 0, this.imageModel.imageWidthInCanvas, this.imageModel.imageHeightInCanvas);
            ctx.fillStyle = 'rgba(0, 0, 0, 0.4)';
            ctx.fillRect(0, 0,this.imageModel.imageWidthInCanvas, this.imageModel.imageHeightInCanvas);
            // draw selection
            this.theSelection.draw(imageModel.k, this.imageModel.image);
        },

		initialize: function() {
            var canvas = document.getElementById('panel');
            var ctx = canvas.getContext('2d');
            //create initial selection
            this.theSelection = new selectionModel(200, 200, 200, 200);
            this.imageModel = new imageModel(ctx, "../static/picture-2.jpg");

            var self = this;
            this.imageModel.on("drawImage", function(event){
                self.drawScene();
            });
            this.imageModel.on("changeCanvasSize", function(event){
               self.changeCanvasSize();
            });

            var panel = $('#panel');
            panel.mousemove(function(e) { // binding mouse move event
                self.theSelection.onmoveHanfler(e);
                self.drawScene();
            });

            panel.mousedown(function(e) {
                self.theSelection.onDownHandler(e);
            });

            panel.mouseup(function(e) {
               self.theSelection.onUpHandler(e);
            });
        },

        getResults : function() {
            var temp_ctx, temp_canvas;
            temp_canvas = document.createElement('canvas');
            temp_ctx = temp_canvas.getContext('2d');
            //temp_canvas.width = this.w;
            //temp_canvas.height = this.h;
            //temp_ctx.drawImage(image, thisA.x*thisA.k, thisA.y*thisA.k, thisA.w*thisA.k, thisA.h*thisA.k, 0, 0, thisA.w, thisA.h);
            //var vData = temp_canvas.toDataURL();
            //$('#crop_result').attr('src', vData);
            $('#results h2').text('Well done, we have prepared our cropped image, now you can save it if you wish');
        }
    });
    return Cropper;
});