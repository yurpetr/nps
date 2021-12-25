const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
	purge:
		[
			'./src/**/*.html',
			'./src/**/*.css',
		],
	darkMode: false, // or 'media' or 'class'
	theme: {
		extend: {
			fontFamily: {
				sans: ['Inter var', ...defaultTheme.fontFamily.sans],
			},
			colors: {
				darkred: {
					light: '#A60B2D',
					DEFAULT: '#5c6ac4',
					dark: '#6f001f',
				}
			}
		}
	},
	variants: {
		extend: {},
	},
	plugins: [
		require('@tailwindcss/forms'),
	]
};