/**
 * Basis Misc
 */

// ID.View.Component
var id_view_cmp = '#id_view_cmp';

var id_roots_language = '#roots_language'

// load component snip "source" to "ID.View.Component"
function load_cmp_view(source) {
	$.get(source, function(data) {
		$(id_view_cmp).html(data);
	})
}

// translate
function translate(language) {

	var load_file = false;

	if (language == null) {
		language = sy_lang;
	}

	// load language file
	jQuery.i18n.properties({
		name : 'lang',
		path : sy_path_lang,
		mode : 'both',
		language : language
	});

	// set translated text
	for ( var key in jQuery.i18n.map) {
		$('#' + key).text(jQuery.i18n.map[key]);
	}

	// set current language to menu
	$(id_roots_language).text(language);
}