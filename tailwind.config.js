const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
	content:
		[
			'./src/**/*.html',
			'./src/**/*.css',
		],
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
		},
		container: {
			center: true,
		}
	},
	variants: {
		extend: {},
	},
	plugins: [
		require('@tailwindcss/forms'),
	]
};