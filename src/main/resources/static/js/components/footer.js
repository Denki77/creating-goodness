let now = new Date();
var divFooter = {
    render: function (createElement) {
        var year = "Все права защищены. 2017-" + now.getFullYear();
        return createElement(
            'div',
            {class: {'blog-footer': true},},
            [
                createElement('a', {attrs: {name: 'up', href: '/'}}, ['На главную']),
                createElement('br'),
                createElement('p', year),
                createElement('p', [createElement('a', {attrs: {name: 'up', href: '#'}}, ['Наверх'])])
            ]
        )
    },
}