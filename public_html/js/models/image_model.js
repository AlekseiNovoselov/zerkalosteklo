define([
    // Libs
    'backbone'
], function(Backbone) {
    var imageModel = Backbone.Model.extend({
        defaults: {
            image : null,
            k : 0, // коэффициент сжатия изображения
            imageWidthInCanvas : 0,
            imageHeightInCanvas : 0,
            ctx : null,
            readyToDraw: false
        },
        setImageSizeInCanvasWindow : function () {
                this.imageWidthInCanvas = this.image.width;
                this.imageHeightInCanvas = this.image.height;
                var kWidth = this.imageWidthInCanvas / this.ctx.canvas.width;
                var kHeight = this.imageHeightInCanvas / this.ctx.canvas.height;
                if (kWidth > 1) {
                    this.imageWidthInCanvas = this.imageWidthInCanvas / kWidth;
                    this.imageHeightInCanvas = this.imageHeightInCanvas / kWidth;
                    kHeight = this.imageHeightInCanvas / this.ctx.canvas.height;
                    this.k = kWidth;
                }
                if (kHeight > 1) {
                    this.imageHeightInCanvas = this.imageHeightInCanvas / kHeight;
                    this.imageWidthInCanvas = this.imageWidthInCanvas / kHeight;
                    this.k = kHeight;
                }
                else {
                    this.k = 1
                }
                this.readyToDraw = true;
                this.trigger("changeCanvasSize", [this]);
                this.trigger("drawImage",[ this ]);
        },
        initialize : function (ctx, src) {
            this.ctx = ctx;
            var self = this;
            var image = new Image();
            image.onload = function () {
                self.setImageSizeInCanvasWindow();
            };
            image.src = src;
            this.image = image;
        }
    });
    return imageModel;
});









