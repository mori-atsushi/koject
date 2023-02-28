// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Koject',
  tagline: 'DI Container Library for Kotlin Multiplatform',
  favicon: 'img/favicon.ico',

  url: 'https://mori-atsushi.github.io',
  baseUrl: '/koject/',
  organizationName: 'mori-atsushi',
  projectName: 'koject', 
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  trailingSlash: false,

  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl:
            'https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      // Replace with your project's social card
      image: 'img/docusaurus-social-card.jpg',
      navbar: {
        title: 'Koject',
        items: [
          {
            href: 'https://github.com/mori-atsushi/koject',
            label: 'GitHub',
            position: 'right',
          },
          {
            to: 'docs/setup',
            label: 'Setup',
            position: 'left',
          },
          {
            type: 'dropdown',
            label: 'Usage',
            position: 'left',
            items: [
              {
                to: 'docs/usage/basic',
                label: 'Start Koject',
              },
              {
                to: 'docs/usage/qualifier',
                label: 'Qualifier',
              },
              {
                to: 'docs/usage/binds',
                label: 'Binds',
              },
            ],
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [],
        copyright: `Copyright © ${new Date().getFullYear()} Koject. Built with Docusaurus.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
        additionalLanguages: ['kotlin'],
      },
    }),
};

module.exports = config;
