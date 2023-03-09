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
          editUrl: 'https://github.com/Mori-Atsushi/koject/tree/main/docs/',
        },
        blog: {
          showReadingTime: true,
          blogSidebarTitle: 'All posts',
          blogSidebarCount: 'ALL',
          editUrl: 'https://github.com/Mori-Atsushi/koject/tree/main/docs/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  plugins: [
    [
      '@docusaurus/plugin-content-blog',
      {
        id: 'japanese-blog',
        routeBasePath: 'blog/jp/',
        path: './blog-jp',
        showReadingTime: false,
        blogSidebarTitle: '全ての投稿',
        blogSidebarCount: 'ALL',
        editUrl: 'https://github.com/Mori-Atsushi/koject/tree/main/docs/',
      },
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      image: 'img/ogp.png',
      navbar: {
        title: 'Koject',
        items: [
          {
            to: 'docs/setup',
            label: 'Setup',
            position: 'left',
          },
          {
            type: 'dropdown',
            label: 'Docs',
            position: 'left',
            items: [
              {
                to: 'docs/core/basic',
                label: 'Core',
              },
              {
                to: 'docs/android',
                label: 'Android',
              },
              {
                to: 'docs/compose',
                label: 'Compose',
              },
            ],
          },
          {
            href: '/api/index.html',
            label: 'API',
            position: 'left',
            target: '_blank',
          },
          {
            type: 'dropdown',
            label: 'Blog',
            position: 'left',
            items: [
              {
                href: '/blog',
                label: 'English',
              },
              {
                href: '/blog/jp',
                label: '日本語',
              },
            ],
          },
          {
            href: 'https://github.com/mori-atsushi/koject',
            label: 'GitHub',
            position: 'right',
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
